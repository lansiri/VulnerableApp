package org.sasanlabs.internal.utility;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Test;

class LegacyReversibleCryptoRetirementTest {

    @Test
    void passwordUtilitiesDoNotExposeLegacyReversibleTransforms() {
        Set<String> legacyMethods = Set.of("encodeBase64", "caesarCipher", "customCipher");

        assertFalse(hasAnyMethodNamed(EncodingUtils.class, legacyMethods));
        assertFalse(hasAnyMethodNamed(EncryptionUtils.class, legacyMethods));
    }

    private boolean hasAnyMethodNamed(Class<?> type, Set<String> methodNames) {
        return Arrays.stream(type.getDeclaredMethods())
                .map(Method::getName)
                .anyMatch(methodNames::contains);
    }
}
