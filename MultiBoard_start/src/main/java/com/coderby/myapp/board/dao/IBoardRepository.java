package com.coderby.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coderby.myapp.board.model.Board;
import com.coderby.myapp.board.model.BoardFile;

public interface IBoardRepository {
	int selectMaxArticleNo();
	
	void insertArticle(Board board);
	void insertFileData(BoardFile file);
	
	List<Board> selectArticleList(@Param("start") int start, @Param("end") int end);
	List<Board> selectArticleListByCategory(@Param("categoryId") int categoryId, @Param("start") int start, @Param("end") int end);
	
	Board selectArticle(int boardId);
	BoardFile getFile(int fileId);
		
	void updateReadCount(int boardId);

	void updateReplyNumber(@Param("masterId") int masterId, @Param("replyNumber") int replyNumber);
	void replyArticle(Board boardId);
	
	String getPassword(int boardId);
	
	void updateArticle(Board board);
	void updateFileData(BoardFile file);
	
	void deleteFileData(int boardId);
	Board selectDeleteArticle(int boardId);
	void deleteArticleByBoardId(int boardId);
	void deleteArticleByMasterId(int boardId);
	
	int selectTotalArticleCount();
	
	int selectTotalArticleCountByCategoryId(int categoryId);


	List<Board> searchListByContentKeyword(String keyword);
}
