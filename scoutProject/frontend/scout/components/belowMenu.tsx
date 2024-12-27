import { Link } from "@react-navigation/native";
import { StyleSheet, Text, View, TouchableOpacity } from "react-native";

function BelowMenu(){
    return(
        <View style={styles.view}>
            <Link screen="" params={{}} style={styles.optionsText}>Champions</Link>
            <Link screen="" params={{}} style={styles.optionsText}>Teams</Link>
            <Link screen="" params={{}} style={styles.optionsText}>Players</Link>
        </View>
    );
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

export default BelowMenu;