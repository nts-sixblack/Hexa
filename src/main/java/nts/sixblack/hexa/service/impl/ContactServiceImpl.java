package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.ContactRepository;
import nts.sixblack.hexa.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;
}
