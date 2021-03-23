package com.answer.biz;

import java.util.List;

import com.answer.dao.AnswerDao;
import com.answer.dao.AnswerDaoImpl;
import com.answer.dto.AnswerDto;

public class AnswerBizImpl implements AnswerBiz {

	private AnswerDao dao = new AnswerDaoImpl();
	
	@Override
	public List<AnswerDto> selectList() {
		return dao.selectList();
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		return dao.selectOne(boardno);
	}

	@Override
	public boolean boardInsert(AnswerDto dto) {
		return dao.boardInsert(dto);
	}

	@Override
	public boolean boardUpdate(AnswerDto dto) {
		return dao.boardUpdate(dto);
	}

	@Override
	public boolean boardDelete(int boardno) {
		return dao.boardDelete(boardno);
	}

	@Override
	public int answerProc(AnswerDto dto) {
		// business logic(service logic) 에서 transaction 처리(rollback , commit)
		// 원래는 트랜잭션 처리해야하나, 간단하게 넘어감
		//두개의 것을 묶어서 하나의 행동처럼 하려고 한다- 하나 실패하면 모두 실패하게 해야함
		
		//비즈하나에서 다오의 2개 연결해준거-> 기능 하나로 만들어준것 
		//다오의 메소드하나는 하나의 기능
		//답변기능을 묶어준것(update와 insert 이 두개가 묶여져야 답변기능이 됨)
		//이작업을 한개만하면 답변 기능이 아님 ->트랜잭션
		int update = dao.answerUpdate(dto.getBoardno());
		int insert = dao.answerInsert(dto);
		
		return update+insert;
	}

}
