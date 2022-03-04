package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}