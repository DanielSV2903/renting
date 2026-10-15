package cr.ac.ucr.paraiso.dsw4.renting.data;

import org.springframework.data.jpa.repository.JpaRepository;

import cr.ac.ucr.paraiso.dsw4.renting.domain.Actor;

public interface ActorData extends JpaRepository<Actor, Integer> {
}
