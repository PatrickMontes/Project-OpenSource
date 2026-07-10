package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.CargoDTO;
import pe.edu.upc.projectopensource.entity.Cargo;
import pe.edu.upc.projectopensource.mapper.CargoMapper;
import pe.edu.upc.projectopensource.repository.CargoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;
    private final CargoMapper cargoMapper;


    public CargoDTO crearCargo(CargoDTO cargoDTO){
        Cargo cargo = cargoMapper.toEntity(cargoDTO);
        return cargoMapper.toDto(cargoRepository.save(cargo));
    }


    public List<CargoDTO> obtenerCargos(){
        return cargoRepository.findAll().stream()
                .map(cargoMapper::toDto)
                .toList();
    }


    public CargoDTO obtenerCargo(Long id){
        return cargoRepository.findById(id)
                .map(cargoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("No se obtuvo el cargo"));
    }


    public CargoDTO actualizarCargo(Long id, CargoDTO cargoDTO){
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se obtuvo el cargo"));

        cargo.setNombre(cargoDTO.getNombre());
        cargo.setSalarioSemanal(cargoDTO.getSalarioSemanal());

        return cargoMapper.toDto(cargoRepository.save(cargo));
    }


    public void eliminarCargo(Long id){
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se obtuvo el cargo"));

        cargoRepository.delete(cargo);
    }
}
