package com.admin.catalago.infrasctructure.castmember.models;

import com.admin.catalago.JacksonTest;
import com.admin.catalago.domain.Fixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

@JacksonTest
class CreateCastMemberRequestTest {

    @Autowired
    private JacksonTester<CreateCastMemberRequest> json;

    @Test
    public void testUnmarshall() throws Exception {
        final var expectedName = Fixture.name();
        final var expectedType = Fixture.CastMembers.type();

        final var json = """
                {
                  "name": "%s",
                  "type": "%s"
                }
                """.formatted(expectedName, expectedType);

        final var actualJson = this.json.parse(json);

        Assertions.assertThat(actualJson)
                .hasFieldOrPropertyWithValue("name", expectedName)
                .hasFieldOrPropertyWithValue("type", expectedType);
    }
}