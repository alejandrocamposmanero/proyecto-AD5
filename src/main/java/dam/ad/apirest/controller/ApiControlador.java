package dam.ad.apirest.controller;

import dam.ad.apirest.model.Cliente;
import dam.ad.apirest.model.Habitacion;
import dam.ad.apirest.repository.ClienteRepository;
import dam.ad.apirest.repository.HabitacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiControlador {

    private ClienteRepository clienteRepo;
    private HabitacionRepository habitacionRepo;

    public ApiControlador(ClienteRepository clienteRepo, HabitacionRepository habitacionRepo){
        this.clienteRepo = clienteRepo;
        this.habitacionRepo = habitacionRepo;
    }

    @GetMapping("/habitaciones")
    public List<Habitacion> getAllHabitaciones(){
        return habitacionRepo.findAll();
    }

    @GetMapping("/habitacion")
    public Optional<Habitacion> getHabitacionById(@RequestParam int id){
        return habitacionRepo.findById(id);
    }

    @PostMapping("/habitacion")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveHabitacion(@RequestBody Habitacion h){
        habitacionRepo.save(h);
    }

    @PutMapping("/habitacion")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateHabitacion(@RequestParam int id, @RequestBody Habitacion h){
        Habitacion habitacion = habitacionRepo.findById(id).orElse(new Habitacion());
        habitacion.setPrecio(h.getPrecio());
        if(h.getCliente() != null)
            habitacion.setCliente(h.getCliente());
        habitacion.setNumCamas(h.getNumCamas());
        habitacionRepo.save(habitacion);
    }

    @DeleteMapping("/delete-habitacion/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHabitacion(@PathVariable int id){
        habitacionRepo.deleteById(id);
    }

    @GetMapping("/clientes")
    public List<Cliente> getAllClientes(){
        return clienteRepo.findAll();
    }

    @GetMapping("/cliente")
    public Optional<Cliente> getClienteById(@RequestParam int id){
        return clienteRepo.findById(id);
    }

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCliente(@RequestBody Cliente c){
        clienteRepo.save(c);
    }

    @PutMapping("/cliente")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@RequestParam int id, @RequestBody Cliente c){
        Cliente cliente = clienteRepo.findById(id).orElse(new Cliente());
        cliente.setDni(c.getDni());
        cliente.setHabitaciones(c.getHabitaciones());
        cliente.setNombre(c.getNombre());
        clienteRepo.save(cliente);
    }

    @DeleteMapping("/delete-cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable int id){
        clienteRepo.deleteById(id);
    }

    @PutMapping("/cliente/{id}")
    public void putHabitacion(@PathVariable int id, @RequestBody Habitacion h){
        Habitacion habitacion;
        if(!habitacionRepo.existsById(h.getId())){
            habitacionRepo.save(h);
            habitacion = h;
        } else {
            habitacion = habitacionRepo.findById(h.getId()).orElse(new Habitacion());
        }
        Cliente cliente = clienteRepo.findById(id).orElse(new Cliente());
        cliente.addHabitacion(habitacion);
        clienteRepo.save(cliente);
    }

}
