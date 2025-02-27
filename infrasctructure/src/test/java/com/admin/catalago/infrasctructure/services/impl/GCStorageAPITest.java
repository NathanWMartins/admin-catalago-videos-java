package com.admin.catalago.infrasctructure.services.impl;

import com.admin.catalago.domain.Fixture;
import com.admin.catalago.domain.resource.Resource;
import com.admin.catalago.domain.utils.IdUtils;
import com.admin.catalago.domain.video.VideoMediaType;
import com.google.api.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;

class GCStorageAPITest {
    private GCStorageService target;

    private Storage storage;

    private String bucket = "fc3_test";

    @BeforeEach
    public void setUp(){
        this.storage = Mockito.mock(Storage.class);
        this.target = new GCStorageService(this.bucket, this.storage);
    }

    @Test
    public void givenValidResource_whenCallsStore_shouldPersistIt(){
        //given
        final var expectedName = IdUtils.uuid();
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.VIDEO);

        final var blob = mockBlob(expectedName, expectedResource);
        doReturn(blob).when(storage).create(any(BlobInfo.class), any());

        //when
        this.target.store(expectedName, expectedResource);

        //then
        final var captor = ArgumentCaptor.forClass(BlobInfo.class);

        Mockito.verify(storage, times(1)).create(captor.capture(), eq(expectedResource.content()));
        final var actualBlob = captor.getValue();
        Assertions.assertEquals(this.bucket, actualBlob.getBlobId().getBucket());
        Assertions.assertEquals(expectedName, actualBlob.getBlobId().getName());
        Assertions.assertEquals(expectedName, actualBlob.getName());
        Assertions.assertEquals(expectedResource.checksum(), actualBlob.getCrc32cToHexString());
        Assertions.assertEquals(expectedResource.contentType(), actualBlob.getContentType());
    }

    @Test
    public void givenValidResource_whenCallsGet_shouldRetrieveIt(){
        //given
        final var expectedName = IdUtils.uuid();
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.VIDEO);

        final var blob = mockBlob(expectedName, expectedResource);
        doReturn(blob).when(storage).get(anyString(), anyString());

        //when
        final var actualResource = this.target.get(expectedName).get();

        //then
        Mockito.verify(storage, times(1)).get(eq(this.bucket), eq(expectedName));

        Assertions.assertEquals(expectedResource, actualResource);
    }

    @Test
    public void givenInvalidResource_whenCallsGet_shouldBeEmpty(){
        //given
        final var expectedName = IdUtils.uuid();
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.VIDEO);

        doReturn(null).when(storage).get(anyString(), anyString());

        //when
        final var actualResource = this.target.get(expectedName);

        //then
        Mockito.verify(storage, times(1)).get(eq(this.bucket), eq(expectedName));

        Assertions.assertTrue(actualResource.isEmpty());
    }

    @Test
    public void givenValidPrefix_whenCallList_shouldRetrieveAll(){
        //given
        final var expectedPrefix = "media_";

        final var expectedNameVideo = IdUtils.uuid();
        final var expectedVideo = Fixture.Videos.resource(VideoMediaType.VIDEO);

        final var expectedNameBanner = IdUtils.uuid();
        final var expectedBanner = Fixture.Videos.resource(VideoMediaType.BANNER);

        final var expectedResources = List.of(expectedNameBanner, expectedNameVideo);
        final var blobVideo = mockBlob(expectedNameVideo, expectedVideo);
        final var blobBanner = mockBlob(expectedNameBanner, expectedBanner);

        final var page = Mockito.mock(Page.class);
        //doReturn(List.of(blobVideo, blobBanner)).when(page).iterateAll();

        doReturn(page).when(storage).list(anyString(), any());

        //when
        final var actualResource = this.target.list(expectedPrefix);

        //then
        Mockito.verify(storage, times(1)).list(eq(this.bucket), eq(Storage.BlobListOption.prefix(expectedPrefix)));

        Assertions.assertTrue(expectedResources.size() == actualResource.size()
        && expectedResources.containsAll(actualResource));
    }


    @Test
    public void givenValidNames_whenCallDelete_shouldDeleteAll(){
        //given
        final var expectedPrefix = "media_";

        final var expectedNameVideo = IdUtils.uuid();
        final var expectedNameBanner = IdUtils.uuid();

        final var expectedResources = List.of(expectedNameBanner, expectedNameVideo);

        //when
        this.target.deleteAll(expectedResources);

        //then
        final var captor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(storage, times(1)).delete(captor.capture());

        final var actualResources = ((List<BlobId>) captor.getValue()).stream()
                        .map(BlobId::getName)
                                .toList();

        Assertions.assertTrue(expectedResources.size() == actualResources.size()
                && expectedResources.containsAll(actualResources));
    }
    private Blob mockBlob(final String name, final Resource expectedResource) {
        final var blob = Mockito.mock(Blob.class);
        Mockito.when(blob.getBlobId()).thenReturn(BlobId.of(this.bucket, name));
        Mockito.when(blob.getCrc32cToHexString()).thenReturn(expectedResource.checksum());
        Mockito.when(blob.getContent()).thenReturn(expectedResource.content());
        Mockito.when(blob.getContentType()).thenReturn(expectedResource.contentType());
        Mockito.when(blob.getName()).thenReturn(expectedResource.name());

        return blob;
    }
}