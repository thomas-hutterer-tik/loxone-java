package cz.smarteon.loxone

import cz.smarteon.loxone.message.MessageKind
import cz.smarteon.loxone.message.ValueEvent
import spock.lang.Specification

import java.nio.ByteBuffer
import java.nio.ByteOrder

import static cz.smarteon.loxone.Codec.hexToBytes


class CodecTest extends Specification {

    def "should read MessageHeader"() {
        given:
        def buffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
                .put(0x03 as byte)
                .put(MessageKind.TEXT.ordinal() as byte)
                .put(0x01 as byte)
                .put(0x00 as byte)
                .putInt(573)

        when:
        def header = Codec.readHeader(buffer)

        then:
        header.kind == MessageKind.TEXT
        header.sizeEstimated
        header.messageSize == 573
    }

    def "should read ValueEvents"() {
        given:
        def bytes = hexToBytes('649a860f00029b0affffd4c75dbaf53c0000000000001440649a860f0002ad0affffd4c75dbaf53c0000000000d07c40649a860f0002b00affffd4c75dbaf53c0000000000c08d40649a860ff6016c0affffd4c75dbaf53c0000000000000000829a860f7c02cd0cffff373f9870b52a0000000000000000fea2860f7803083effffb2d4efc8b5b600000000000000003b3ea710c501ff18ffff373f9870b52a00000000000000003b3ea710c5010019ffff373f9870b52a00000000000000003b3ea710c5010119ffff373f9870b52a00000000000000003b3ea710c5010219ffff373f9870b52a0000000000000000fea2860f7803113effffb2d4efc8b5b60000000000000000fea2860f7803fb3dffffb2d4efc8b5b60000000000c082403b3ea710c5010419ffff373f9870b52a000000000000f03ffea2860f7803093effffb2d4efc8b5b60000000000000000fea2860f78030a3effffb2d4efc8b5b60000000000000000fea2860f78030b3effffb2d4efc8b5b60000000000000000fea2860f78030c3effffb2d4efc8b5b60000000000000000fea2860f78030d3effffb2d4efc8b5b60000000000000000fea2860f78030e3effffb2d4efc8b5b60000000000000000fea2860f78030f3effffb2d4efc8b5b60000000000000000fea2860f7803103effffb2d4efc8b5b60000000000000000fea2860f7803e83dffffb2d4efc8b5b60000000000000000fea2860f7803e93dffffb2d4efc8b5b60000000000000000fea2860f7803ea3dffffb2d4efc8b5b60000000000000000fea2860f7803eb3dffffb2d4efc8b5b60000000000000000fea2860f7803ec3dffffb2d4efc8b5b600000000000000000da2860f01031218ffff6ad2ef881eaf00000000000000000da2860f01031018ffff6ad2ef881eaf00000000000000000da2860f0103f817ffff6ad2ef881eaf00000000000000000da2860f0103f917ffff6ad2ef881eaf00000000000000000da2860f0103fa17ffff6ad2ef881eaf00000000000000000da2860f0103fb17ffff6ad2ef881eaf00000000000000000da2860f0103fc17ffff6ad2ef881eaf00000000000000000da2860f0103fd17ffff6ad2ef881eaf00000000000000000da2860f01030e18ffff6ad2ef881eaf00000000000000000da2860f01030f18ffff6ad2ef881eaf00000000000000000da2860f01031118ffff6ad2ef881eaf00000000000000000da2860f01030118ffff6ad2ef881eaf00000000000000000da2860f01030218ffff6ad2ef881eaf00000000000000000da2860f01030318ffff6ad2ef881eaf00000000000000000da2860f01030418ffff6ad2ef881eaf00000000000000000da2860f01030518ffff6ad2ef881eaf00000000000000000da2860f01030618ffff6ad2ef881eaf00000000000000003b3ea710d501351affff373f9870b52a00000000000000000da2860f9d008c17ffff373f9870b52a00000000000000000da2860fad02f017ffff373f9870b52a00000000000000000da2860f9d008917ffff0beffc15bedd00000000000018400da2860f9d007e17ffff0beffc15bedd00000000000000000da2860f9d007117ffff0beffc15bedd00000000000000000da2860f9d007217ffff0beffc15bedd00000000000059400da2860f9d006f17ffff0beffc15bedd000000000000004007778b0fdc004910ffff373f9870b52a000000000000000007778b0fdc004310ffff747a5b105600000000000080364007778b0fdc002010ffff747a5b105600000000000000000007778b0fdc004110ffff747a5b105600000000000000000007778b0fdc003f10ffff747a5b105600000000000000f03f07778b0fdc004010ffff747a5b105600000000000000000007778b0fdc001510ffff747a5b105600000000000000f03f07778b0fdc001610ffff747a5b105600000000000000f03f07778b0fdc004610ffff747a5b105600000000000000000007778b0fdc004710ffff747a5b105600000000000000000007778b0fdc003610ffff747a5b105600000000000000594007778b0fdc003a10ffff747a5b105600000000000000000007778b0fdc002110ffff747a5b10560000000000000000003b3ea710c501e219ffffacb819d4bca9000000000000000007778b0fdc001a10ffff747a5b105600000000000000000007778b0fdc001910ffff747a5b105600000000000000000007778b0fdc001b10ffff747a5b105600000000000000000007778b0fdc002810ffff747a5b105600000000000000084007778b0fdc002910ffff747a5b105600000000000080364007778b0fdc002a10ffff747a5b105600000000000000374007778b0fdc002d10ffff747a5b105600000000000000144007778b0fdc002e10ffff747a5b105600000000000080394007778b0fdc002c10ffff747a5b105600000000000000f03f07778b0fdc002b10ffff747a5b105600000000000000f03f07778b0fdc004410ffff747a5b105600000000000000144007778b0fdc004510ffff747a5b1056000000000000001440649a860f0002e90affffd4c75dbaf53c0000000000000000649a860f0002e50affffd4c75dbaf53c0000000000000000')
        def buffer = ByteBuffer.wrap(bytes)

        when:
        def events = Codec.readValueEvents(buffer)

        then:
        events.size() == 79
        events[0].uuid.toString() == '0f869a64-0200-0a9b-ffffd4c75dbaf53c'
        events[0].value == 5.0d
    }

    def "should read TextEvents"() {
        given:
        def bytes = hexToBytes('d69a860fd201d60cffff373f9870b52a00000000000000000000000000000000010000005b000000d69a860fd201d50cffff373f9870b52a00000000000000000000000000000000020000005b5d00001b9c860f79030607ffff91085de3ab7600000000000000000000000000000000020000005b5d00001b9c860f79030707ffff91085de3ab7600000000000000000000000000000000320000005b226873762832352c39362c353029222c226873762832342c39352c383029222c22687376283333372c39342c353029225d00003b3ea7107a018216ffffacb819d4bca9000000000000000000000000000000001d000000323031372d31322d31332031363a33373a3434202b30313a30303a3030000000fea2860f7803153effff373f9870b52a000000000000000000000000000000007f080000323031372d30372d31372031313a33323a3231204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031313a33383a3531204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031313a34303a3538204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031313a34313a3337204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031313a34333a3338204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031333a33373a3331204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031333a34303a3336204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031333a34313a3231204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031343a31313a3436204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031343a31323a3138204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031343a31323a3534204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30372d31372031343a31333a3434204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30382d33312030383a30373a3034204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30382d33312030383a30383a3031204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30382d33312030383a30393a3432204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30392d31382031303a31373a3131204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30392d31382031303a31373a3433204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30392d31382031383a32333a3039204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d30392d31382031383a32343a3533204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d31302d31332031333a32303a3231204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d31302d32352031353a32383a3433204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d31302d32352031353a32393a3235204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d31302d32352031353a32393a3332204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d31302d32352031353a33313a3334204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d6f766553656e736f7220506f687962207c323031372d31302d33302030383a35383a3335204c6fc5be6e696365202f2f20446574656b746f723a2053686f77726f6f6d4d')
        def buffer = ByteBuffer.wrap(bytes)

        when:
        def events = Codec.readTextEvents(buffer)

        then:
        events.size() == 6
        events[0].uuid.toString() == '0f869ad6-01d2-0cd6-ffff373f9870b52a'
        events[0].iconUuid.toString() == '00000000-0000-0000-0000000000000000'
        events[0].text == '['
    }
}
