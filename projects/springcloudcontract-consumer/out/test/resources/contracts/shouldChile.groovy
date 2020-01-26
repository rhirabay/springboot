package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        description("age is over 20, so expected 'adult'")
        method 'POST'
        url '/checkAge'
        body ([
                age: 19
        ])
        headers {
            header 'Content-Type', 'application/json'
        }
    }
    response {
        status 200
        body ([
                result: 'child'
        ])
        headers {
            contentType('application/json')
        }
    }
}
