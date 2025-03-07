package dev.stupak.main.maps.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import dev.stupak.main.maps.regions.getCherkasyRegionMap
import dev.stupak.main.maps.regions.getChernigivRegionMap
import dev.stupak.main.maps.regions.getChernivtsiRegionMap
import dev.stupak.main.maps.regions.getCrimeaRegionMap
import dev.stupak.main.maps.regions.getDnipropetrovskRegionMap
import dev.stupak.main.maps.regions.getDonetskRegionMap
import dev.stupak.main.maps.regions.getIvanoFrankivskRegionMap
import dev.stupak.main.maps.regions.getKharkivRegionMap
import dev.stupak.main.maps.regions.getKhersonRegionMap
import dev.stupak.main.maps.regions.getKhmelnitskRegionMap
import dev.stupak.main.maps.regions.getKirovogradRegionMap
import dev.stupak.main.maps.regions.getKyivRegionMap
import dev.stupak.main.maps.regions.getLuhanskRegionMap
import dev.stupak.main.maps.regions.getLvivRegionMap
import dev.stupak.main.maps.regions.getMykolaivRegionMap
import dev.stupak.main.maps.regions.getOdesaRegionMap
import dev.stupak.main.maps.regions.getPoltavaRegionMap
import dev.stupak.main.maps.regions.getRivneRegionMap
import dev.stupak.main.maps.regions.getSumyRegionMap
import dev.stupak.main.maps.regions.getTernopilRegionMap
import dev.stupak.main.maps.regions.getVinnitsiaRegionMap
import dev.stupak.main.maps.regions.getVolynRegionMap
import dev.stupak.main.maps.regions.getZakarpattiaRegionMap
import dev.stupak.main.maps.regions.getZaporizhzhiaRegionMap
import dev.stupak.main.maps.regions.getZhytomyrRegionMap

fun getRegionMap(regionName: String, mapArgs: MapArgs): ImageVector {
    return when (regionName) {
        "Хмельницька область" -> getKhmelnitskRegionMap(mapArgs)
        "Вінницька область" -> getVinnitsiaRegionMap(mapArgs)
        "Рівненська область" -> getRivneRegionMap(mapArgs)
        "Волинська область" -> getVolynRegionMap(mapArgs)
        "Дніпропетровська область" -> getDnipropetrovskRegionMap(mapArgs)
        "Житомирська область" -> getZhytomyrRegionMap(mapArgs)
        "Закарпатська область" -> getZakarpattiaRegionMap(mapArgs)
        "Запорізька область" -> getZaporizhzhiaRegionMap(mapArgs)
        "Івано-Франківська область" -> getIvanoFrankivskRegionMap(mapArgs)
        "Київська область" -> getKyivRegionMap(mapArgs)
        "Кіровоградська область" -> getKirovogradRegionMap(mapArgs)
        "Луганська область" -> getLuhanskRegionMap(mapArgs)
        "Миколаївська область" -> getMykolaivRegionMap(mapArgs)
        "Одеська область" -> getOdesaRegionMap(mapArgs)
        "Полтавська область" -> getPoltavaRegionMap(mapArgs)
        "Сумська область" -> getSumyRegionMap(mapArgs)
        "Тернопільська область" -> getTernopilRegionMap(mapArgs)
        "Харківська область" -> getKharkivRegionMap(mapArgs)
        "Херсонська область" -> getKhersonRegionMap(mapArgs)
        "Черкаська область" -> getCherkasyRegionMap(mapArgs)
        "Чернігівська область" -> getChernigivRegionMap(mapArgs)
        "Чернівецька область" -> getChernivtsiRegionMap(mapArgs)
        "Львівська область" -> getLvivRegionMap(mapArgs)
        "Донецька область" -> getDonetskRegionMap(mapArgs)
        "Автономна Республіка Крим" -> getCrimeaRegionMap(mapArgs)
        else -> Icons.Default.Info
    }
}

