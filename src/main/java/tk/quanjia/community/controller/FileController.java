package tk.quanjia.community.controller;

import org.springframework.stereotype.Controller;
import tk.quanjia.community.dto.FileDTO;

@Controller
public class FileController {
public FileDTO update(){
    FileDTO fileDTO = new FileDTO();
    fileDTO.setSuccess(1);
    return fileDTO;
}
}
