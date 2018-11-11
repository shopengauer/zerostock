package com.zerodb

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory


@ConfigurationProperties(
        prefix = "spring.datasource.hikari",
        ignoreUnknownFields = false
)
class DbConfig : HikariConfig()

@Configuration
@EnableTransactionManagement
class JpaConfig(val dbConfig: DbConfig) {

    @Bean
    fun hikariDatasource() = HikariDataSource(dbConfig)

    @Bean
    fun localContainerEntityManagerFactoryBean() =
            LocalContainerEntityManagerFactoryBean().let {
                it.setPackagesToScan("com.zero.model")
                it.dataSource = hikariDatasource()
                it.jpaVendorAdapter = jpaVendorAdapter()
                it

            }

    @Bean
    fun jpaVendorAdapter() =
            HibernateJpaVendorAdapter()
                    .let {
                        it.setDatabase(Database.POSTGRESQL)
                        it.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect")
                        it.setShowSql(true)
                        it.setGenerateDdl(true)
                        it.setGenerateDdl(true)
                        it
                    }


    @Bean
    fun transactionManager(emf: EntityManagerFactory) =
            JpaTransactionManager().let {
                it.entityManagerFactory = emf
                it
            }


    @Bean
    fun exceptionTranslation() = PersistenceExceptionTranslationPostProcessor()


}
