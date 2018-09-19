package com.coderby.myapp.board.service;

import java.util.List;

import com.coderby.myapp.board.model.Board;
import com.coderby.myapp.board.model.BoardFile;

public interface IBoardService {
	void insertArticle(Board boardId);
	void insertArticle(Board boardId, BoardFile file);
	
	List<Board> selectArticleList(int page);
	List<Board> selectArticleListByCategory(int categoryId, int page);
	List<Board> selectArticleListByCategory(int categoryId);
	
	Board selectArticle(int boardId);
	
	BoardFile getFile(int fileId);
	
	void replyArticle(Board board);
	void replyArticle(Board board, BoardFile file);

	String getPassword(int boardId);
	
	void updateArticle(Board board);
	void updateArticle(Board board, BoardFile file);
	
	Board selectDeleteArticle(int boardId);
	void deleteArticle(int boardId, int replyNumber);
	
	int selectTotalArticleCount();
	int selectTotalArticleCountByCategoryId(int categoryId);
	
	List<Board> searchListByContentKeyword(String keyword);
}
