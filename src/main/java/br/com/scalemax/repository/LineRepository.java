package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.Line;
import br.com.scalemax.model.dto.LineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LineRepository extends AbstractRepository<Line, LineDTO, Long> {
    Page<Line> filtrando(LineDTO lineDTO, Pageable pageable);
}
