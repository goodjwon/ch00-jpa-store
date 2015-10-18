package jpabook.codes;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryCodeGenerator implements IdentifierGenerator {
	private static Logger log =  LoggerFactory.getLogger(CategoryCodeGenerator.class);

	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

		String prefix = "CA";
		System.out.println(prefix);
		Connection connection = session.connection();
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT nextval ('CATEGORY_SEQ') as nextval");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("nextval");
				String code = prefix + StringUtils.leftPad("" + id, 3, '0');
				log.info("Generated Stock Code: " + code);
				
				System.out.println(code);
				
				return code;
			}

		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new HibernateException("Unable to generate Stock Code Sequence");
		}
		return null;
	}
}