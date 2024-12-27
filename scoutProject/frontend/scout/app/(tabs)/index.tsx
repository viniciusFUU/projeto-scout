import {View} from 'react-native';
import Header from '@/components/header';
import BelowMenu from '@/components/belowMenu';
import Home from '@/pages/home';
export default function HomeScreen() {
  return (
    <View>
        <Header />
        <BelowMenu />
        <Home />
    </View>
  );
};