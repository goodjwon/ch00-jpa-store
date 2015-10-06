package jpabook.codes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class StockCodeGenerator implements IdentifierGenerator {
	private static Logger log = Logger.getLogger(StockCodeGenerator.class);

	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

		String prefix = "M";
		System.out.println(prefix);
		Connection connection = session.connection();
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT nextval ('HIBERNATE_SEQUENCE') as nextval");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("nextval");
				String code = prefix + StringUtils.leftPad("" + id, 3, '0');
				log.debug("Generated Stock Code: " + code);
				return code;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new HibernateException("Unable to generate Stock Code Sequence");
		}
		return null;
	}
}