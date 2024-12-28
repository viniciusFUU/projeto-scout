import { StyleSheet, Text, View, Dimensions } from "react-native";

function Home() {
    const screenHeight = Dimensions.get("window").height;

    return (
        <View style={[styles.container, { height: screenHeight }]}>
            <Text>Home</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#dfebff',
    },
});

export default Home;
