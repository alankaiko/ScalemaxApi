package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.Line;
import br.com.scalemax.model.dto.LineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LineService extends AbstractService<Line, LineDTO> {
    Page<Line> filtrando(LineDTO lineDTO, Pageable pageable);
}
