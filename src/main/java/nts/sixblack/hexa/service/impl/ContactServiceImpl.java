package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.ContactRepository;
import nts.sixblack.hexa.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;
}
