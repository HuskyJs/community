package tk.quanjia.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.quanjia.community.dto.PaginationDTO;
import tk.quanjia.community.dto.QuestionDTO;
import tk.quanjia.community.mapper.QuestionMapper;
import tk.quanjia.community.mapper.UserMapper;
import tk.quanjia.community.model.Question;
import tk.quanjia.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @param page 展示当前页面  从1到n
     * @param size 当前页面数量
     * @return
     */
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        Integer currentPage = paginationDTO.getCurrentPage();
        Integer offset = size * (currentPage - 1);//起始索引

        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        return paginationDTO;
    }
}
