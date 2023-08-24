package com.alviss.invoice_app.invoice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {

//    public Optional<Invoice> findInvoiceById(final String id);

    // public Optional<Invoice> removeById(final String id);
}
