package jpabook.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.model.entity.Category;
import jpabook.model.entity.CategoryItem;
import jpabook.model.entity.Item;
import jpabook.model.entity.Member;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            //TODO 비즈니스 로직
            logic(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }
    
    public static void logic(EntityManager em) {

        String id = "id1";
        Member member = new Member();
        member.setName("goodjwon");
        member.setCity("Seouel");
        member.setStreet("41");
        member.setZipcode("153-013");
        member.setAge(39);

        if(null == getMember(em, "M001")){
            addMember(em, member);
        }


        Category categoryPaerent =  addCateogry(em, "카테고리1");
        Category categoryChild = addChildCategory(em, categoryPaerent);


        addItem(em, categoryPaerent.getId());
        addItem(em, categoryChild.getId());
        
        //회원등록
        //em.persist(member);
        
        //카테고리 등록
        //em.persist(category);

        //물품등록
        
        //수정
//      member.setAge(20);
//
//      //한 건 조회
//      Member findMember = em.find(Member.class, id);
//      System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
//
//      //목록 조회
//      List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
//      System.out.println("members.size=" + members.size());
//
//      //삭제
//      em.remove(member);

    }

    /**
     * get Member info
     * @param em
     * @param memberId
     * @return
     */
    public static Member getMember(EntityManager em, String memberId){

        Member findMember = em.find(Member.class, memberId);
        
        System.out.println("=============="+Member.class.getName());
        
        return findMember;
    }

    /**
     * add MemberInfo
     * @param em
     * @param user
     */
    public static void addMember(EntityManager em, Member user){

        Member member = new Member();
        member.setName(user.getName());
        member.setCity(user.getCity());
        member.setStreet(user.getStreet());
        member.setZipcode(user.getZipcode());

        em.persist(member);
    }

    /**
     * add CategoryInfo
     * @param em
     * @param categoryName
     * @return
     */
    public static Category addCateogry(EntityManager em, String categoryName){

        Category category = new Category();
        category.setName(categoryName);

        em.persist(category);

        return category;
    }

    /**
     * adds childCategorys
     * @param em
     * @param parentCategory
     * @return
     */
    public static Category addChildCategory(EntityManager em, Category parentCategory){
        Category childCategory = new Category();
        childCategory.setName(parentCategory.getName().concat("-부하"));
        childCategory.setParent(parentCategory);

        em.persist(childCategory);

        return childCategory;
    }


    /**
     * 카테고리 정보 get
     * 카테고리 물품 mepping
     * 물품정보 set
     * 물품정보 저장
     * 카테고리 정보 저장
     * @param em
     * @param categoryId
     */
    public static void addItem(EntityManager em, String categoryId){
    	//등록될 카테고리 select
        Category category = em.find(Category.class, categoryId);
    	//물품등록 될 맵핑 데이터 셋팅
        
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setCategory(category);
        
        categoryItem.toString();
        
        Item item = new Item();
        item.setName("테스트 아이템");
        item.setPrice(19852);
        item.setStockQuantity(159);
        
        em.persist(item);
        categoryItem.setItem(item);
        em.persist(categoryItem);
        
        
    	//물품정보 셋팅 정보 저장
    	//물품정보 저장 => 에러 발생 예상, 물품번호가 없는 상태이므로.. 
    }
    
    public static void getItem(EntityManager em){
    	//물품정보 카테고리 정보 포함 get 
    	//물품정보 출력
    }
    
    public static void getItemListByCategory(EntityManager em){
    	//카테고리 별 물품목록 정보 get
    }
    
}
