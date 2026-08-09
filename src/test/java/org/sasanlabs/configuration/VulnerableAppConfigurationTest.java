package org.sasanlabs.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

class VulnerableAppConfigurationTest {

    @Test
    void level9MultipartResolverBoundsFilesBeforeParsingConsumesResources() {
        CommonsMultipartResolver resolver = VulnerableAppConfiguration.level9MultipartResolver();

        assertThat(resolver.getFileUpload().getSizeMax())
                .isEqualTo(VulnerableAppConfiguration.MAX_UPLOAD_SIZE_BYTES);
        assertThat(resolver.getFileUpload().getFileSizeMax())
                .isEqualTo(VulnerableAppConfiguration.MAX_UPLOAD_SIZE_BYTES);
    }
}
