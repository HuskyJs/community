package tk.quanjia.community.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.quanjia.community.dto.PaginationDTO;
import tk.quanjia.community.dto.QuestionDTO;
import tk.quanjia.community.dto.QuestionQueryDTO;
import tk.quanjia.community.exception.CustomizeErrorCode;
import tk.quanjia.community.exception.CustomizeException;
import tk.quanjia.community.mapper.QuestionExtMapper;
import tk.quanjia.community.mapper.QuestionMapper;
import tk.quanjia.community.mapper.UserMapper;
import tk.quanjia.community.model.Question;
import tk.quanjia.community.model.QuestionExample;
import tk.quanjia.community.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    /**
     * @param page 展示当前页面  从1到n
     * @param size 当前页面数量
     * @return
     */
    public PaginationDTO list(String search, String tag, Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search, " ");
            search = Arrays
                    .stream(tags)
                    .filter(StringUtils::isNotBlank)
                    .map(t -> t.replace("+", "").replace("*", "").replace("?", ""))
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining("|"));
        }


        PaginationDTO paginationDTO = new PaginationDTO();

        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);

        if (StringUtils.isNotBlank(tag)) {
            tag = tag.replace("+", "").replace("*", "").replace("?", "");
            questionQueryDTO.setTag(tag);
        }

        Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);

        paginationDTO.setPagination(totalCount, page, size);
        Integer currentPage = paginationDTO.getCurrentPage();
        Integer offset = size * (currentPage - 1);//起始索引


        questionQueryDTO.setSize(size);
        questionQueryDTO.setPage(offset);
        List<Question> questionList = questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setDescription("");
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    //得到分页
    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount =(int) questionMapper.countByExample(questionExample);

        paginationDTO.setPagination(totalCount, page, size);//设置总数量   当前页   页大小
        Integer currentPage = paginationDTO.getCurrentPage();
        Integer offset = size * (currentPage - 1);//起始索引


        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        //用户页面出错时
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }

        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //第一次创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insertSelective(question);
        }else{
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());

            QuestionExample example = new QuestionExample();
            example.createCriteria()
                            .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);

            if(updated != 1){
                //用户页面出错时
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }





    /**
     * 实现阅读数加1
     *          存在并发操作的问题  .... 留坑以后解决
     * @param id
     */
    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    /**
     * 和标签有关
     * @param queryDTO
     * @return
     */
    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if(StringUtils.isBlank(queryDTO.getTag())){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
//        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));//字符串拼接
        String regexpTag = String.join("|", tags);//字符串拼接
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }

    /**
     * 删除当前 id 的问题
     * @param id
     */
    public void deleteById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        questionExtMapper.deleteQuestion(question);
    }
}
