package org.training360.musicstore;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private ModelMapper modelMapper;
    private AtomicLong idGenerator = new AtomicLong();
    private List<Instrument> instruments = new ArrayList<>();

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDTO> listInstruments(Optional<String> brand, Optional<Integer> price) {
        List<Instrument> filtered = instruments.stream()
                .filter(instrument -> brand.isEmpty() || instrument.getBrand().equalsIgnoreCase(brand.get()))
                .filter(instrument -> price.isEmpty() || instrument.getPrice() == price.get())
                .collect(Collectors.toList());

        Type targetListType = new TypeToken<List<InstrumentDTO>>() {}.getType();
        return modelMapper.map(filtered, targetListType);
    }

    public InstrumentDTO createInstrument(CreateInstrumentCommand command) {
        Instrument created = new Instrument(idGenerator.incrementAndGet(), command.getBrand(), command.getType(), command.getPrice());
        instruments.add(created);
        return modelMapper.map(created, InstrumentDTO.class);
    }


    public void deleteAllInstruments() {
        instruments.clear();
        idGenerator = new AtomicLong();
    }


    public InstrumentDTO instrumentById(long id) {
        return modelMapper.map(searchById(id), InstrumentDTO.class);
    }


    private Instrument searchById(long id) {
        return instruments.stream()
                .filter(instrument -> instrument.getId() == id)
                .findAny()
                .orElseThrow(() ->new IllegalArgumentException("Instrument not found: " + id));
    }


    public InstrumentDTO updatePrice(long id, UpdatePriceCommand command) {
        Instrument found = searchById(id);
        if (found.getPrice() != command.getPrice()){
            found.setPrice(command.getPrice());
            found.setPostDate(LocalDate.now());
        }
        return modelMapper.map(found, InstrumentDTO.class);
    }


    public void deleteById(long id) {
        Instrument found = searchById(id);
        instruments.remove(found);
    }
}
