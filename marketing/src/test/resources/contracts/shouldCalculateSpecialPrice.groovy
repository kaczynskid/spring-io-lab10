package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        name('calculate special price')
        method('POST')
        url('/specials/1/calculate')
        body([
                unitPrice: 40.0,
                unitCount: 5
        ])
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body([
                specialId: value(consumer('abcdefghijklmn0123456789'), producer(regex('[a-zA-Z0-9]{24}'))),
                totalPrice: 150.0
        ])
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
}
