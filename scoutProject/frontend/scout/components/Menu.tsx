import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

interface MenuProps{
    onChangeScreen: (screen: string) => void;
}

function Menu ({onChangeScreen}: MenuProps){
    return(
        <View style={styles.view}>
            <TouchableOpacity onPress={() => onChangeScreen('Home')}>
                <Text style={styles.optionsText}>Home</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => onChangeScreen('Championship')}>
                <Text style={styles.optionsText}>Championship</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => onChangeScreen('Teams')}>
                <Text style={styles.optionsText}>Teams</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => onChangeScreen('Players')}>
                <Text style={styles.optionsText}>Players</Text>
            </TouchableOpacity>
        </View>
    )
}

const styles = StyleSheet.create({
    view: {
        backgroundColor: '#000025',
        flexDirection: "row",
        justifyContent: "space-around",
        alignItems: "center",
        height: 40
    },
    optionsText: {
        color: '#fff',
    }
})

export default Menu;