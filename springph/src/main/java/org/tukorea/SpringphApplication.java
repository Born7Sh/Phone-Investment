package org.tukorea;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.tukorea.entity.Member;

@SpringBootApplication
public class SpringphApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringphApplication.class, args);
		// persistence.xml에서 이름이 jpabook인 영속성 유닛 persistence-unit을 찾아서 엔티티 매니저 팩토리를 생성한다.
		//엔티티 매니저 팩토리는 애플리케이션 전체에서 딱 한 번만 생성하고 공유해서 사용해야 한다.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		
		// 엔티티 메니저를 사용해서 엔티티를 데이터 베이스에 등록/수정/삭제/조회 할 수 있다.
		// 엔티티 매니저는 데이터베이스 커넥션과 밀접한 관계가 있으므로 스레드 간에 공유하거나 재사용하면 안된다.
		// 이게 메인이라서 그렇지 웹 어플리케이션에서는 고객의 요청이 들어올 때마다 생성해서 써야 함. 
		EntityManager em = emf.createEntityManager();
		
		// jpa의 모든 활동은 트랜잭션 안에서 이루어져야 함. 트랜잭션 사용.
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			//logic(em);
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();

		
		System.out.println("Hello");
	}

	/*
	public static void logic(EntityManager em) {
		String id = "1234";
		Member member = new Member();
		member.setId(id);
		member.setUsername("bad");
		member.setAge(25);
		
		em.persist(member);
		
		member.setAge(30);
		
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember=" + findMember.getUsername() + ", age = "+findMember.getAge());
		
		// select m from Member m 은 JPQL 
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("members.size = " + members.size());
		
		//em.remove(member);
	}*/

}
