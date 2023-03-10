package orsys.projet.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import orsys.projet.business.Location;
import orsys.projet.business.Parasol;
import orsys.projet.dto.ConcessionnaireDto;
import orsys.projet.dto.LocataireDto;
import orsys.projet.dto.LocationDto;
import orsys.projet.dto.LocationDtoEx;
import orsys.projet.dto.ParasolDto;
import orsys.projet.dto.StatutDto;
import orsys.projet.mapper.ConcessionnaireMapper;
import orsys.projet.mapper.ConcessionnaireMapperImpl;
import orsys.projet.mapper.LocataireMapper;
import orsys.projet.mapper.LocationMapper;
import orsys.projet.mapper.ParasolMapper;
import orsys.projet.mapper.StatutMapper;
import orsys.projet.mapper.StatutMapperImpl;
@Component
public class LocationMapperImpl implements LocationMapper {

	@Override
	public LocationDto toDto(Location location) {
		StatutMapper statutMapper = new StatutMapperImpl();
		LocataireMapper locataireMapper = new LocataireMapperImpl();
		LocataireDto locataireDto = locataireMapper.toDto(location.getLocataire());
		ConcessionnaireMapper concessionnaireMapper = new ConcessionnaireMapperImpl();
		ConcessionnaireDto concessionnaireDto = concessionnaireMapper.toDto(location.getConcessionnaire());
		if(concessionnaireDto!=null) {concessionnaireDto.setRole("concessionnaire");}
		StatutDto statutDto = statutMapper.toDto(location.getStatut());
		return new LocationDto(location.getId(), location.getDateDebut(), location.getDateFin(), location.getMontantAReglerEnEuros(),
				locataireDto, location.getRemarque(), concessionnaireDto, statutDto,
				(byte) location.getParasols().size());
	}

	@Override
	public LocationDtoEx toDtoEx(Location location) {
		ParasolMapper parasolMapper = new ParasolMapperImpl();
		LocataireMapper locataireMapper = new LocataireMapperImpl();
		StatutMapper statutMapper = new StatutMapperImpl();
		ConcessionnaireMapper concessionnaireMapper = new ConcessionnaireMapperImpl();
		LocataireDto locataireDto = locataireMapper.toDto(location.getLocataire());
		ConcessionnaireDto concessionnaireDto = concessionnaireMapper.toDto(location.getConcessionnaire());
		StatutDto statutDto = statutMapper.toDto(location.getStatut());
		LocationDtoEx locationDtoEx = new LocationDtoEx(location.getId(),location.getDateDebut(), location.getDateFin(),
				location.getMontantAReglerEnEuros(), locataireDto, location.getRemarque(), concessionnaireDto,
				statutDto, (byte) location.getParasols().size());
		List<ParasolDto> parasols = new ArrayList<>();
		for (Parasol parasol : location.getParasols()) {
			parasols.add(parasolMapper.toDto(parasol, location.getDateDebut(), location.getDateFin()));
		}
		locationDtoEx.setParasols(parasols);
		return locationDtoEx;
	}

	@Override
	public Location toEntity(LocationDto locationDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocationDto> toDto(List<Location> locations) {
		List<LocationDto> locationDtos = new ArrayList<>();
		for (Location location : locations) {
			locationDtos.add(toDto(location));
		}
		return locationDtos;
	}

}
