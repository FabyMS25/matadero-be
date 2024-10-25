package gamq.recaudaciones.matadero.config;


import gamq.recaudaciones.matadero.audit.AuditorAwareimpl;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

public class JpaAuditConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareimpl();
    }

}
