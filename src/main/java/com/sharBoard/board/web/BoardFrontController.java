package com.sharBoard.board.web;

import com.sharBoard.board.service.BoardService;
import com.sharBoard.board.service.BoardVO;
import com.sharBoard.board.service.Impl.BoardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class BoardFrontController {

    @Resource(name = "boardService")
    private BoardService boardService;

//    @RequestMapping("/")
//    public String listViewBoard(BoardVO boardVO, Model model)throws Exception{
//       List<?> resultList = boardService.listBoard(boardVO);
//       model.addAttribute("resultList",resultList);
//
//        return "page/board/front/listViewBoard";
//    }
}
