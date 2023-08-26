
export function validateNotBlank(value, fieldName) {
    if (!value || value.trim() === '') {
        return `${fieldName} must be provided.`;
    }
    return null;
}

export function validateNotNull(value, fieldName) {
    if (value === null || value === undefined) {
        return `${fieldName} must be provided.`;
    }
    return null;
}

export function validatePositiveNumber(value, fieldName) {
    if (typeof value !== 'number' || value <= 0) {
        return `${fieldName} must be a positive number.`;
    }
    return null;
}

export function validateForm(fields) {
    let errors = {};

    errors.billerStreetAddress = validateNotBlank(fields.billerStreetAddress, 'Biller Street Address');
    errors.billerCity = validateNotBlank(fields.billerCity, 'Biller City');
    errors.billerZipCode = validatePositiveNumber(fields.billerZipCode, 'Biller Zip Code');
    errors.billerCountry = validateNotBlank(fields.billerCountry, 'Biller Country');

    errors.clientName = validateNotBlank(fields.clientName, 'Client Name');
    errors.clientStreetAddress = validateNotBlank(fields.clientStreetAddress, 'Client Street Address');
    errors.clientEmail = validateNotBlank(fields.clientEmail, 'Client Email');
    errors.clientCity = validateNotBlank(fields.clientCity, 'Client City');
    errors.clientZipCode = validatePositiveNumber(fields.clientZipCode, 'Client Zip Code');
    errors.clientCountry = validateNotBlank(fields.clientCountry, 'Client Country');

    errors.paymentTerms = validatePositiveNumber(fields.paymentTerms, 'Payment Terms');

    errors.productDesc = validateNotBlank(fields.productDesc, 'Product Description');
    
    if (!fields.invoiceItemList || fields.invoiceItemList.length === 0) {
        errors.invoiceItemList = 'Valid Invoice items must be provided.';
    } else {
        // Validate each item in the invoiceItemList
        let itemErrors = [];

        fields.invoiceItemList.forEach((item, index) => {
            let itemError = {};

            itemError.id = validateNotBlank(item.id, `Invoice Item ${index + 1} ID`);
            itemError.itemName = validateNotBlank(item.itemName, `Invoice Item ${index + 1} Item Name`);

            itemError.qty = validateNotNull(item.qty, `Invoice Item ${index + 1} Quantity`);
            if (!itemError.qty) {
                itemError.qty = validatePositiveNumber(item.qty, `Invoice Item ${index + 1} Quantity`);
            }

            itemError.price = validateNotNull(item.price, `Invoice Item ${index + 1} Price`);
            if (!itemError.price) {
                itemError.price = validatePositiveNumber(item.price, `Invoice Item ${index + 1} Price`);
            }

            itemErrors.push(itemError);
        });

        errors.invoiceItemList = itemErrors;
    }

    // Check for errors and return true if form is valid
    return [Object.values(errors).every(error => {
        if (Array.isArray(error)) {
            return error.every(itemError => Object.values(itemError).every(subError => !subError));
        }
        return !error;
    }), errors];
}