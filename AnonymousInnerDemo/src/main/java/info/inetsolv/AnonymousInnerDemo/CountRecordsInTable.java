package info.inetsolv.AnonymousInnerDemo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CountRecordsInTable {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("spring.xml");
		JdbcTemplate jdbcTemplate = container.getBean(JdbcTemplate.class);
		
		String sql = "select count(*) abc from emp_tbl";
		Integer count = jdbcTemplate.query(sql,
			new ResultSetExtractor<Integer>() {

				@Override
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					int count = 0;
					if(rs.next()) {
						count = rs.getInt("abc");
					}
					return count;
				}
			}
		);
		System.out.println(count);
		((AbstractApplicationContext)container).close();
	}
}
