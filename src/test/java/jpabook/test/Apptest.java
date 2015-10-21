package jpabook.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Apptest {
	
	private final static Logger log = LoggerFactory.getLogger(Apptest.class);
	
	private static final String PERSISTENCE_UNIT = "jpabook";
    private static EntityManagerFactory emf;
    private EntityManager em;
	
    /**
     * Provide a setUpClass() method that runs once before all tests that can create the entity manager. 
     * This method must be static.
     * @throws Exception
     */
    @BeforeClass
	public static void setUpClass() throws Exception {
		
		log.info("create entity manager factory");
		emf =Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}
	
    
    /**
     * Provide a setUp() method that will be called before each testMethod is executed. 
     * Have this method create an entity manager for the tests to use.
     * @throws Exception
     */
	@Before
	public void setUp() throws Exception {
		log.info("create entity manager");
		em = emf.createEntityManager();
	}
	
	/**
	 * Provide a tearDown() method that will be called after each testMethod. 
	 * Have this flush all remaining items in the persistence context to the database and close the entity manager.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
        try {
            log.info("tearDown() started, em=" + em);
            em.getTransaction().begin();
            em.flush();            
            //logAutos();            
            em.getTransaction().commit();            
            em.close();
            log.info("tearDown() complete, em=" + em);
        }
        catch (Exception ex) {
            log.error("tearDown failed", ex);
            
            throw ex;
        }
     }
	
	/**
	 * Provide a tearDownClass() method that will be called after all testMethods have completed. 
	 * This method must be static and should close the entity manager factory
	 */
	@AfterClass
	public static void tearDownClass() {
		log.info("closing entity manager factory");
		emf.close();
	 }
	
	 
	@Test
	public void test() {
	
	}

}
