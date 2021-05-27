package rhirabay;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Component
//public class CustomBatchConfigurer extends BasicBatchConfigurer {
//    public CustomBatchConfigurer(BatchProperties properties, DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
//        super(properties, createMetaDatasource(), transactionManagerCustomizers);
//    }
//
//    private HikariDataSource createMetaDatasource() {
//        var config = new HikariConfig();
//        config.setJdbcUrl( "jdbc:h2:./h2db/meta" );
//        config.setUsername("sa");
//        config.setDriverClassName("org.h2.Driver");
//        return new HikariDataSource( config );
//    }
//}
