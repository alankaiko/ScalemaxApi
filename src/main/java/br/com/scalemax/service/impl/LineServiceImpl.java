package br.com.scalemax.service.impl;

import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.Line;
import br.com.scalemax.model.dto.LineDTO;
import br.com.scalemax.repository.LineRepository;
import br.com.scalemax.service.LineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LineServiceImpl extends AbstractServiceImpl<Line, LineDTO> implements LineService {
    private final LineRepository lineRepository;

    public LineServiceImpl(LineRepository lineRepository) {
        super(lineRepository);
        this.lineRepository = lineRepository;
    }

    @Override
    public Page<Line> filtrando(LineDTO lineDTO, Pageable pageable) {
        return this.lineRepository.filtrando(lineDTO, pageable);
    }
}
