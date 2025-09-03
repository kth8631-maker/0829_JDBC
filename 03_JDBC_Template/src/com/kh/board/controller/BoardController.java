package com.kh.board.controller;

import java.util.List;

import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

public class BoardController {
	// 중간 제어자 역할(흐름처리)	
	public int insertBoard(BoardDTO bd) {
		int result = new BoardService().insertBoard(bd);
		return result;
	}
	
	public List<Board> selectBoardList(){
		List<Board> boards = new BoardService().selectBoardList();
		return boards;
	}
	
	public Board selectBoard(int boardNo) {
		Board board = new BoardService().selectBoard(boardNo);
		return board;
	}
	
	public int deleteBoard(int boardNo) {
		
		int result = new BoardService().deleteBoard(boardNo);
		return result;
		
	}

}
