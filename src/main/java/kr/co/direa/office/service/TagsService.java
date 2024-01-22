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
        List<String> newTagList = new ArrayList<>();
        if (request.get("tag") != null && ((List<String>) request.get("tag")).size() != 0) {
            newTagList = (List<String>) request.get("tag");
            for (int i=0 ; newTagList.size()>i ; i++) {
                Tags tag = tagsRepository.findByName(newTagList.get(i));
                if (tag != null){
                    log.info("hhhhhhhhhhhhhhhhh: {}", newTagList.get(i));
                } else {
                    DeviceTag deviceTag = new DeviceTag();
                    deviceTag.setDevice(device);
                    deviceTag.setTag();
                    log.info("kkkkkkkkkkkkkkkkk: {}", newTagList.get(i));
                }
            }
        }


    }
}
