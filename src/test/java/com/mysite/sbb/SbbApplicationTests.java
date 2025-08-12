package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	//add
	@Test
	void testJpa01 () {
		Question q1 = new Question();
		q1.setSubject("안녕하세요.");
		q1.setContent("반갑습니다.");
		q1.setCreateDate(LocalDateTime.now());

		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("111");
		q2.setContent("111");
		q2.setCreateDate(LocalDateTime.now());

		this.questionRepository.save(q2);
	}

	//find all
	@Test
	void testJpa02 () {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("안녕하세요.", q.getSubject());
	}

	//find by id
	@Test
	void testJpa03 () {
		Optional<Question> oq = this.questionRepository.findById(2);
		if (oq.isPresent()) {
			Question q = oq.get();
			assertEquals("111", q.getSubject());
		}
	}

	//find by subject
	@Test
	void testJpa04 () {
		Question q = this.questionRepository.findBySubject("안녕하세요.");
		assertEquals(1, q.getId());
	}

	//find by subject and content
	@Test
	void testJpa05 () {
		Question q = this.questionRepository.findBySubjectAndContent("안녕하세요.", "반갑습니다.");
		assertEquals(1, q.getId());
	}

	//find by subject like
	// subject 열 값들 중에 특정 문자열을 포함하는 데이터를 조회
	@Test
	void testJpa06 () {
		List<Question> qList = this.questionRepository.findBySubjectLike("안녕%");
		Question q = qList.get(0);
		assertEquals("안녕하세요.", q.getSubject());
	}
}
