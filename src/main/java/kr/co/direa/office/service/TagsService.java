package kr.co.direa.office.service;

import jakarta.transaction.Transactional;
import kr.co.direa.office.domain.DeviceTag;
import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.domain.Tags;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.DeviceTagRepository;
import kr.co.direa.office.repository.DevicesRepository;
import kr.co.direa.office.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagsService {
    private final DevicesRepository devicesRepository;
    private final TagsRepository tagsRepository;
    private final DeviceTagRepository deviceTagRepository;

    List<Tags> getTagsByDeviceId(String deviceId){

        return null;
    }
    Tags getTagByName(String name){

        return null;
    }
    Tags createTag(Tags tag){

        return null;
    }

    void deleteTagsByDeviceId(String deviceId){
        deviceTagRepository.deleteByDeviceId(deviceId);
    }

    public void updateByDeviceId(Map<String, Object> request) {
        Devices device = devicesRepository.findById(request.get("deviceId").toString())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + request.get("deviceId")));
        deleteTagsByDeviceId(device.getId());
        List<String> newTagList = (List<String>) request.getOrDefault("tag", new ArrayList<>());
        List<DeviceTag> deviceTagsToSave = new ArrayList<>();
        newTagList.forEach(tagName -> {
            Tags tag = findOrSaveTag(tagName);
            deviceTagsToSave.add(new DeviceTag(device, tag));
        });
        deviceTagRepository.saveAll(deviceTagsToSave);
    }

    private Tags findOrSaveTag(String tagName) {
        Tags tag = tagsRepository.findByName(tagName);
        if (tag == null) {
            tag = tagsRepository.save(new Tags(tagName)); // 없으면 tag 테이블에도 저장
        }
        return tag;
    }
}
