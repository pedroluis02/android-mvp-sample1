package com.github.pedroluis02.android_mvp_sample1.data

import java.lang.Exception

open class DuplicateElementException(entityName: String, fieldName: String, fieldValue: String)
    : Exception("$entityName($fieldName=$fieldValue) already registered.")

class CompanyAlreadyExistsException(value: String)
    : DuplicateElementException ("Company", "id", value)

class EmployeeAlreadyExistsException(value: String)
    : DuplicateElementException ("Employee", "id", value)