import { StyleSheet, Text, View } from "react-native";

function Championship(){
    return(
        <View style={styles.container}>
            <Text>Olá champion</Text>
        </View>
    )

}

const styles = StyleSheet.create({
    container: {
        backgroundColor: "#a4a4a4"
    }
})

export default Championship;