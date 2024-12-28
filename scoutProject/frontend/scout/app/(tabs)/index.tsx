import { useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import Menu from "@/components/Menu";
import Championship from "@/pages/championship";
import Header from "@/components/header";
import Home from "@/pages/home";
import Teams from "@/pages/teams";
import Players from "@/pages/players";

function HomeScreen() { 
    const [currentScreen, setCurrentScreen] = useState('Home');

    const renderScreen = () => {
        switch (currentScreen) {
            case 'Home':
                return (
                    <Home />
                );
            case 'Championship':
                return <Championship />;
            case 'Teams':
                return <Teams />
            case 'Players':
                return <Players/>
            default:
                return (
                    <Home />
                );
        }
    };

    return (
        <View style={styles.container}>
            <Header />
            <Menu onChangeScreen={setCurrentScreen} />
            {renderScreen()}
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#dfebff',
    },
});

export default HomeScreen;
