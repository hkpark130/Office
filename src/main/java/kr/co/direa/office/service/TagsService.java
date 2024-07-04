package kr.co.direa.office.service;

import kr.co.direa.office.domain.DeviceTag;
import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.domain.Tags;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.DeviceTagRepository;
import kr.co.direa.office.repository.DevicesRepository;
import kr.co.direa.office.repository.TagsRepository;
import kr.co.direa.office.vo.DeviceApplicationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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

    public void updateByDeviceId(DeviceApplicationVo request) {
        Devices device = devicesRepository.findById(request.getDeviceId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_DEVICE,
                        "해당 기기가 없습니다. deviceId=" + request.getDeviceId()));
        deleteTagsByDeviceId(device.getId());
        List<String> newTagList = (request.getTag()!=null)?request.getTag():new ArrayList<>();
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
