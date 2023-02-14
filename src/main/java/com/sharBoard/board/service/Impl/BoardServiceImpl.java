package com.sharBoard.board.service.Impl;

import com.sharBoard.board.mapper.BoardMapper;
import com.sharBoard.board.service.BoardService;
import com.sharBoard.board.service.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Resource(name = "boardMapper")
    private BoardMapper boardMapper;
    @Override
    public List<?> listBoard(BoardVO boardVO) throws Exception {

        return boardMapper.listBoard(boardVO);
    }
}
