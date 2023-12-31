import app from '../../config/configApp';
import Cards from '../../components/cards/frame/CardsFrame.vue';
import FeatherIcons from '../../components/utilities/featherIcons.vue';
import Button from '../../components/buttons/Buttons.vue';
import BtnGroup from '../../components/buttons/ButtonsGroup.vue';
import CalendarButton from '../../components/buttons/CalendarButton.vue';
import ExportButton from '../../components/buttons/ExportButton.vue';
import ShareButton from '../../components/buttons/ShareButton.vue';
import { PageHeader } from '../../components/pageHeaders/PageHeaders.vue';
import Heading from '../../components/heading/Heading';
import AutoComplete from '../../components/autoComplete/autoComplete';
import Dropdown from '../../components/dropdown/Dropdown';
import Popover from '../../components/popup/Popup';
import Modal from '../../components/modals/Modals';

[
  Heading,
  Cards,
  Button,
  BtnGroup,
  CalendarButton,
  ExportButton,
  ShareButton,
  { name: 'PageHeader', ...PageHeader },
  AutoComplete,
  Dropdown,
  Popover,
  FeatherIcons,
  Modal,
].map((c) => {
  app.component(`sd${c.name}`, c);
});
