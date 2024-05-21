package kr.co.direa.office.service;

import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.repository.DevicesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvExportService {
    private final DevicesRepository devicesRepository;

    public void writeDevicesToCsv(Writer writer, String status, Boolean equalsCondition) {
        List<Devices> devicesList = (equalsCondition)?
                devicesRepository.findByStatus(status):devicesRepository.findByStatusNot(status);

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(
                    "id","categoryName","manageDepName","projectName","status","purpose",
                    "spec","price","model","company","sn","isUsable","purchaseDate","description",
                    "adminDescription"
            );
            for (Devices device : devicesList) {
                DeviceDto deviceDto = new DeviceDto(device);
                csvPrinter.printRecord(
                        deviceDto.getId(), deviceDto.getCategoryName(), deviceDto.getManageDepName(),
                        deviceDto.getProjectName(), deviceDto.getStatus(), deviceDto.getPurpose(), deviceDto.getSpec(),
                        deviceDto.getPrice(), deviceDto.getModel(), deviceDto.getCompany(), deviceDto.getSn(),
                        deviceDto.getIsUsable(), deviceDto.getPurchaseDate(), deviceDto.getDescription(),
                        deviceDto.getAdminDescription()
                );
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
}
