package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Cargo;
import pe.edu.upc.projectopensource.repository.CargoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;


    public Cargo crearCargo(Cargo cargo){
        return cargoRepository.save(cargo);
    }


    public List<Cargo> obtenerCargos(){
        return cargoRepository.findAll();
    }


    public Cargo obtenerCargo(Long id){
        return cargoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se obtuvo el cargo"));
    }


    public Cargo actualizarCargo(Long id, Cargo cargoActualizada){
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se obtuvo el cargo"));

        cargo.setNombre(cargoActualizada.getNombre());
        cargo.setSalarioSemanal(cargoActualizada.getSalarioSemanal());

        return cargoRepository.save(cargo);
    }


    public void eliminarCargo(Long id){
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se obtuvo el cargo"));

        cargoRepository.delete(cargo);
    }
}
