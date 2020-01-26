package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        description("age is over 20, so expected 'adult'")
        method 'POST'
        url '/checkAge'
        body ([
                age: 20
        ])
        headers {
            contentType('application/json')
        }
    }
    response {
        status 200
        body ([
                result: 'adult'
        ])
        headers {
            contentType('application/json')
        }
    }
}
