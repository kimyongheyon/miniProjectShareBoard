package com.sharBoard.board.mapper;

import com.sharBoard.board.service.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "boardMapper")
public interface BoardMapper {

    public List<BoardVO> listBoard (BoardVO boardVO);

}
