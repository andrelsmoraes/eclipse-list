package andrelsmoraes.eclipselist.api.controller;

import andrelsmoraes.eclipselist.application.usecase.type.ListTypeUseCase;
import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    private final ListTypeUseCase listTypeUseCase;
    private final TypeMapper mapper;

    public TypeController(ListTypeUseCase listTypeUseCase, TypeMapper mapper) {
        this.listTypeUseCase = listTypeUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public List<String> listAll() {
        return listTypeUseCase.execute()
                .stream()
                .map(mapper::toString)
                .toList();
    }
}
