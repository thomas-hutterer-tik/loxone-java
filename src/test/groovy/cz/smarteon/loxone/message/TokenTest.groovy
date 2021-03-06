package cz.smarteon.loxone.message

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification

import java.time.LocalDateTime

import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals
import static net.javacrumbs.jsonunit.core.util.ResourceUtils.resource
import static spock.util.matcher.HamcrestSupport.that

class TokenTest extends Specification implements SerializationSupport {

    def "should deserialize"() {
        when:
        Token token = readResource('message/token.json', Token)

        then:
        token.token == '8E2AA590E996B321C0E17C3FA9F7A3C17BD376CC'
        token.key == [68, 68, 50] as byte[]
        token.validUntil == 342151839
        token.rights == 1666
        token.unsecurePassword == false
    }

    def "test valid until"() {
        when:
        def validUntil = 60 + System.currentTimeSeconds() - 1230768000
        def token = new Token('aa', [1] as byte[], validUntil.intValue(), 0, true)

        println(token.getValidUntilDateTime())

        then:
        def expire = token.getSecondsToExpire()
        expire <= 60
        expire >= 0
        token.getValidUntilDateTime().isAfter(LocalDateTime.now())
    }

    def "should serialize"() {
        expect:
        that new Token('8E2AA590E996B321C0E17C3FA9F7A3C17BD376CC', [68, 68, 50] as byte[], 342151839, 1666, false),
                jsonEquals(resource('message/token.json'))
    }

    def "should verify equals"() {
        expect:
        EqualsVerifier.forClass(Token).usingGetClass().verify()
    }
}
